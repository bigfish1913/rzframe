package learnJava.rz20200309.ParseClass;


import com.rz.frame.utils.JsonUtils;
import learnJava.rz20200307.CodeInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ClassApp {
	public static void main(String[] args) throws Exception {
		ClassComplieFile classFile = new ClassComplieFile("D:\\work\\loadClass\\learnJava\\rz20200307\\Demo");
		
		
		while (classFile.hasNext()) {
			System.out.printf(classFile.getHexNext(2));
			System.out.printf(" ");
		}
		System.out.println(" ");
		classFile.reset();
		try {
			ClassParseInfo classParseInfo = new ClassParseInfo();
			MagicCodeInfo magicCodeInfo = new MagicCodeInfo();
			magicCodeInfo.process(classFile);
			VersionInfo versionInfo = new VersionInfo();
			versionInfo.process(classFile);
			
			ConstantInfo constantInfo = new ConstantInfo();
			Integer count = classFile.getIntNext(2);
			constantInfo.setConstantCount(count);
			
			HashMap<String, List<String>> constantMap = new HashMap<>();
			for (int i = 0; i < count - 1; i++) {
				String typeKey = classFile.getNext(1);
			
				String contentType = ClassMainData.Costanst_map.get(typeKey);
				String indexStr = ClassMainData.Costanst_map.get(contentType);
				String key = String.format("%s_%s_%s", contentType, i, typeKey);
				String[] indexArr = indexStr.split(",");
				System.out.println("Key:"+key);
				System.out.println(indexStr);
				constantMap.put(key, new LinkedList<>());
				for (int j = 0; j < indexArr.length; j++) {
					String next = classFile.getNext(Integer.parseInt(indexArr[j]));
					constantMap.get(key).add(next);
					System.out.println("Hex:"+next);
					if (typeKey.equals("1")) {
						next = classFile.getStrNext(Integer.parseInt(next));
						constantMap.get(key).add(next);
					}
					
//					System.out.println(next);
				}
			}
			
			
			classParseInfo.setMagicCodeInfo(magicCodeInfo);
			classParseInfo.setVersionInfo(versionInfo);
			classParseInfo.setConstantInfo(constantMap);
			int accFlag = Integer.parseInt(classFile.getNext(2));
			
			classParseInfo.setAccessFlag(ClassUtil.getAccFlag(accFlag));
			classParseInfo.setClassIndex(classFile.getNext(2));
			classParseInfo.setParentIndex(classFile.getNext(2));
			classParseInfo.setInterFaceIndex(classFile.getNext(2));
			
			int fieldCount = classFile.getIntNext(2);
			classParseInfo.setFiledCount(fieldCount);
			ArrayList<FieldInfo> filedInfos = new ArrayList<>();
			for (int i = 0; i < classParseInfo.getFiledCount(); i++) {
				FieldInfo filedInfo = new FieldInfo();
				filedInfo.setAccess_flags(ClassUtil.getAccFlag(classFile.getIntNext(2)));
				filedInfo.setName_index(classFile.getIntNext(2));
				filedInfo.setDescriptor_index(classFile.getIntNext(2));
				filedInfo.setAttributes_count(classFile.getIntNext(2));
				if (filedInfo.getAttributes_count() > 0) {
					String attrInfo = classFile.getHexNext(filedInfo.getAttributes_count());
					filedInfo.setAttributeInfo(attrInfo);
				}
				filedInfos.add(filedInfo);
			}
			classParseInfo.setFiledInfoList(filedInfos);
			
			//方法读取
			int methodCount = classFile.getIntNext(2);
			classParseInfo.setMethodCount(methodCount);
			System.out.println(JsonUtils.serializeObject(classParseInfo));
			ArrayList<FieldInfo> methodInfos = new ArrayList<>();
			for (int i = 0; i < methodCount; i++) {
				FieldInfo filedInfo = new FieldInfo();
				filedInfo.setAccess_flags(ClassUtil.getAccFlag(classFile.getIntNext(2)));
				filedInfo.setName_index(classFile.getIntNext(2));
				filedInfo.setDescriptor_index(classFile.getIntNext(2));
				filedInfo.setAttributes_count(classFile.getIntNext(2));
				if (filedInfo.getAttributes_count() > 0) {
					CodeInfo codeInfo = new CodeInfo();
					codeInfo.setAttributeNameIndex(classFile.getIntNext(2));
					codeInfo.setAttributeLenght(classFile.getIntNext(4));
					codeInfo.setMaxStack(classFile.getIntNext(2));
					codeInfo.setMaxLocals(classFile.getIntNext(2));
					codeInfo.setCodeLength(classFile.getIntNext(4));
					String code = classFile.getHexNext(codeInfo.getCodeLength());
					
					String jvmCmd = ClassUtil.getJvmCmd(code);
					codeInfo.setCode(jvmCmd);
					
					String tag=classFile.getNext(2);
					  tag=classFile.getNext(2);
					System.out.println(tag);
					
					//					codeInfo.setExceptionTableLength(classFile.getIntNext(4));
					//					String expection = StringUtil.hexStringToString(classFile.getHexNext(codeInfo.getExceptionTableLength()));
					//					codeInfo.setExceptionTable(expection);
					//
					//					codeInfo.setAttributeLenght(classFile.getIntNext(4));
					//					String attributeInfo = StringUtil.hexStringToString(classFile.getNext(codeInfo.getAttributeLenght()));
					//					codeInfo.setAttributeInfo(attributeInfo);
				}
				methodInfos.add(filedInfo);
				
			}
			classParseInfo.setMethodCount(methodCount);
			classParseInfo.setMethodList(methodInfos);
			System.out.println(JsonUtils.serializeObject(classParseInfo));
			
		} catch (Exception ex) {
			ex.printStackTrace();
			
		}
	}
}
