package ${packageName}.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import ${packageName}.model.${className}Model;

public interface ${className}Manager extends BaseManager {

	${className}Model get(${pkFieldType} id);

	List<${className}Model> getAll();

	List<${className}Model> findByExample(${className}Model example);

	${className}Model save(${className}Model model);

	List<${className}Model> saveAll(Collection<${className}Model> models);

	void remove(${className}Model model);

	void removeAll(Collection<${className}Model> models);

	void removeByPk(${pkFieldType} id);

	void removeAllByPk(Collection<${pkFieldType}> ids);

}
