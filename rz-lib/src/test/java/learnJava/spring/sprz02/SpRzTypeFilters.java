package learnJava.spring.sprz02;

import com.rz.frame.utils.RzLogger;
import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

public class SpRzTypeFilters implements TypeFilter {
	/**
	 * 此处写一个Demo 只加载Controller的代码
	 * metadataReader 读取当前正在扫描的类的信息
	 * metadataReaderFactory 可以获得其他类的所有信息
	 */
	@Override
	public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
		AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
		ClassMetadata classMetadata = metadataReader.getClassMetadata();
		Resource resource = metadataReader.getResource();
		
		String clazzName=classMetadata.getClassName();
		RzLogger.info("自定义Type获得当前类的信息：",clazzName);
		if(clazzName.endsWith("Controller"))return true;
		return false;
	}
}
