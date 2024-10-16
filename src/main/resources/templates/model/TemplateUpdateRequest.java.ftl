package ${packageName}.model.dto.${dataKey};

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 更新${dataName}请求
 *
 * @author shing
 */
@Data
public class ${upperDataKey}UpdateRequest implements Serializable {

    ${field}
    private static final long serialVersionUID = 1L;
}