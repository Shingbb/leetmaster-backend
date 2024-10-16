package ${packageName}.model.dto.${dataKey};

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 创建${dataName}请求
 *
 * @author shing
 */
@Data
public class ${upperDataKey}AddRequest implements Serializable {

    ${field}
    private static final long serialVersionUID = 1L;
}