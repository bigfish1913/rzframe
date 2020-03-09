package learnJava.rz20200307;

import com.rz.frame.utils.JsonUtils;
import com.rz.frame.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ClassReader {
	
	
	public static void main(String[] args) throws Exception {
		ClassFile classFile = new ClassFile("D:\\work\\loadClass\\learnJava\\rz20200307\\Demo");
		ClassInfo classInfo = new ClassInfo();
		
		while (classFile.hasNext()) {
			System.out.printf(classFile.getHexNext(2));
			System.out.printf(" ");
		}
		System.out.println(" ");
		classFile.reset();
		try {
			classInfo.setMagicCode(classFile.getHexNext(ClassEnum.Magic_Code.getCode()));
			classInfo.setJdkVersion(classFile.getNext(ClassEnum.JDK_Version.getCode()));
			String jdkVersionKey = classFile.getNext(ClassEnum.JDK_Main_Version.getCode());
			classInfo.setJdkMainVersion(ClassMainData.JdkVersion.get(jdkVersionKey));
			
			Integer constantCount = classFile.getIntNext(ClassEnum.Constant_Count.getCode());
			classInfo.setConstanstCount(constantCount.toString());
			List<ConstantInfo> constantInfoList = new ArrayList<>();
			StringBuilder sb = new StringBuilder();
			//这里需要-1,因为索引是从1开始
			for (int i = 0; i < constantCount - 1; i++) {
				String hex = classFile.getHexNext(2);
				Integer typeKey = Integer.parseInt(hex, 16);
				String contentType = ClassMainData.Costanst_map.get(typeKey.toString());
				String indexStr = ClassMainData.Costanst_map.get(contentType);
				String[] indexArr = indexStr.split(",");
				ConstantInfo constantInfo = new ConstantInfo(hex, contentType, classFile.getNext(Integer.parseInt(indexArr[1]) * 2));
				if (indexArr.length > 2) {
					int length = Integer.parseInt(indexArr[2]) * 2;
					if (hex.equals("01")) {
						length = Integer.parseInt(constantInfo.getArgs1()) * 2;
					}
					constantInfo.setArgs2(classFile.getHexNext(length));
				}
				//				System.out.println("index"+i+"："+constantInfo);
				constantInfoList.add(constantInfo);
				
				if (!hex.equals("01")) {
					String format = String.format("#%s=%s  #%s", (i + 1), constantInfo.getType(), constantInfo.getValue1(), constantInfo.getValue2());
					
					if (StringUtils.isNotEmpty(constantInfo.getArgs2())) {
						format += ".#" + constantInfo.getValue2();
					}
					sb.append("\n");
					sb.append(format);
				} else {
					sb.append(String.format("#%s=%s  %s\n", (i + 1), constantInfo.getType(), constantInfo.getText()));
				}
				
			}
			classInfo.setConstantContent(sb.toString());
			int accFlag = Integer.parseInt(classFile.getNext(4));
			
			classInfo.setAccessFlag(AccUtil.getAccFlag(accFlag));
			classInfo.setClassIndex(classFile.getNext(4));
			classInfo.setParentIndex(classFile.getNext(4));
			classInfo.setInterFaceIndex(classFile.getNext(4));
			
			classInfo.setFiledCount(classFile.getIntNext(4));
			ArrayList<FiledInfo> filedInfos = new ArrayList<>();
			for (int i = 0; i < classInfo.getFiledCount(); i++) {
				FiledInfo filedInfo = new FiledInfo();
				filedInfo.setAccFlag(AccUtil.getAccFlag(classFile.getIntNext(4)));
				filedInfo.setNameIndex(classFile.getNext(4));
				filedInfo.setDecrInfo(classFile.getNext(4));
				filedInfo.setAttributteCount(classFile.getIntNext(4));
				if (filedInfo.getAttributteCount() > 0) {
					String attrInfo = classFile.getHexNext(filedInfo.getAttributteCount());
					filedInfo.setAttrInfo(StringUtil.hexStringToString(attrInfo));
				}
				filedInfos.add(filedInfo);
			}
			classInfo.setFiledInfoList(filedInfos);
			//方法读取
			int methodCount = classFile.getIntNext(4);
			classInfo.setMethodCount(methodCount);
			ArrayList<FiledInfo> methodInfos = new ArrayList<>();
			for (int i = 0; i < methodCount; i++) {
				FiledInfo filedInfo = new FiledInfo();
				filedInfo.setAccFlag(AccUtil.getAccFlag(classFile.getIntNext(4)));
				filedInfo.setNameIndex(classFile.getNext(4));
				filedInfo.setDecrInfo(classFile.getNext(4));
				filedInfo.setAttributteCount(classFile.getIntNext(4));
				if (filedInfo.getAttributteCount() > 0) {
					CodeInfo codeInfo = new CodeInfo();
					codeInfo.setAttributeNameIndex(classFile.getIntNext(4));
					codeInfo.setAttributeLenght(classFile.getIntNext(8));
					codeInfo.setMaxStack(classFile.getIntNext(4));
					codeInfo.setMaxLocals(classFile.getIntNext(4));
					codeInfo.setCodeLength(classFile.getIntNext(8));
					String code = classFile.getHexNext(codeInfo.getCodeLength()*2);
				 
					String jvmCmd = AccUtil.getJvmCmd(code);
					codeInfo.setCode(jvmCmd);
					
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
			classInfo.setMethodCount(methodCount);
			classInfo.setMethodList(methodInfos);
			
			/*CodeInfo codeInfo=new CodeInfo();
			codeInfo.setAttributeNameIndex(classFile.getIntNext(4));
			codeInfo.setAttributeLenght(classFile.getIntNext(8));
			codeInfo.setMaxStack(classFile.getIntNext(4));
			codeInfo.setMaxLocals(classFile.getIntNext(4));
			codeInfo.setCodeLength(classFile.getIntNext(8));
			String code=StringUtil.hexStringToString(classFile.getNext(codeInfo.getCodeLength()));
			codeInfo.setCode(code);
			
			codeInfo.setExceptionTableLength(classFile.getIntNext(4));
			String expection=StringUtil.hexStringToString(classFile.getNext(codeInfo.getExceptionTableLength()));
			codeInfo.setExceptionTable(expection);
			
			codeInfo.setAttributeLenght(classFile.getIntNext(4));
			String attributeInfo=StringUtil.hexStringToString(classFile.getNext(codeInfo.getAttributeLenght()));
			codeInfo.setAttributeInfo(attributeInfo);
			classInfo.setCodeInfo(codeInfo);*/
			//			System.out.println(classFile.getHexNext(4));
			System.out.println(JsonUtils.serializeObject(classInfo));
		} catch (Exception ex) {
			ex.printStackTrace();
			
		}
	}
	
}
