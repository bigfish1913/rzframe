package learnJava.rz20200309.ParseClass;


import com.rz.frame.utils.JsonUtils;
import learnJava.rz20200307.CodeInfo;
import learnJava.rz20200309.ParseClass.Constant.CONSTANT_Fieldref_info;
import learnJava.rz20200309.ParseClass.Constant.CONSTANT_Integer_info;
import learnJava.rz20200309.ParseClass.Constant.CONSTANT_Methodref_info;
import learnJava.rz20200309.ParseClass.Constant.CONSTANT_Utf8_info;

import java.util.ArrayList;

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
			classParseInfo.setMagicCode(classFile.getHexNext(4));
			
			classParseInfo.setVersionInfo(classFile.getNext(2));
			String jdkVersion = classFile.getNext(2);
			classParseInfo.setJdkVersionInfo(ClassMainData.JdkVersion.get(jdkVersion));
			
			
			Integer count = classFile.getIntNext(2);
			classParseInfo.setConstantCount(count);
			
			for (int i = 0; i < count - 1; i++) {
				ConstantEnum constantType = ConstantEnum.getMessageType(classFile.getIntNext(1));
				switch (constantType) {
					case CONSTANT_Utf8_info: {
						classParseInfo.pushConstant(i,constantType.getCode(), new CONSTANT_Utf8_info(constantType.getCode(),classFile));
						break;
					}
					
					case CONSTANT_Fieldref_info: {
						classParseInfo.pushConstant(i,constantType.getCode(), new CONSTANT_Fieldref_info(constantType.getCode(),classFile));
						break;
					}
					case CONSTANT_Integer_info: {
						classParseInfo.pushConstant(i,constantType.getCode(), new CONSTANT_Integer_info(constantType.getCode(),classFile));
						break;
					}
					case CONSTANT_Methodref_info: {
						classParseInfo.pushConstant(i,constantType.getCode(), new CONSTANT_Methodref_info(constantType.getCode(),classFile));
						break;
					}
					
					
				}
				
				
			}
			
			
			//			classParseInfo.setConstantInfo(constantMap);
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
					
					//					String tag=classFile.getNext(2);
					//					  tag=classFile.getNext(2);
					//					System.out.println(tag);
					
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
