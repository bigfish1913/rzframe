package learnJava.spring.sprz08;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class RzImportSelector implements ImportSelector {
	/**
	 * 返回值
	 * 返回想注入的类的列表
	 * @param importingClassMetadata
	 * @return
	 */
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		return new String[]{
				"learnJava.spring.sprz08.Fish"
		};
	}
}
