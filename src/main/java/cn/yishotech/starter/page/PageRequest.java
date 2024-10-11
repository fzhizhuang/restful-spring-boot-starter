/**
 * 项目名称:  restful-spring-boot-starter
 * 公司名称:  YiShoTech
 * All rights Reserved, Designed By YiShoTech 2023-2024
 */
package cn.yishotech.starter.page;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>类路径:cn.yishotech.starter.request.PageRequest</p>
 * <p>类描述:分页请求</p>
 * <p>创建人:jason zong</p>
 * <p>创建时间:2024/10/12 02:21</p>
 */
@Data
@Schema(description = "分页请求")
public class PageRequest {

    @Schema(description = "页码, 默认第一页")
    private Integer pageNum = 1;

    @Schema(description = "每页显示条数, 默认10条")
    private Integer pageSize = 10;

    public void  setPageNum(Integer pageNum) {
        if (pageNum == null || pageNum < 1) {
            this.pageNum = 1;
        } else {
            this.pageNum = pageNum;
        }
    }

    public void  setPageSize(Integer pageSize) {
        if (pageSize == null || pageSize < 1) {
            this.pageSize = 10;
        } else {
            this.pageSize = pageSize;
        }
    }
}
