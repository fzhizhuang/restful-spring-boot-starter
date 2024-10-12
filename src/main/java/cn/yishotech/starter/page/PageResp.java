/**
 * 项目名称:  restful-spring-boot-starter
 * 公司名称:  YiShoTech
 * All rights Reserved, Designed By YiShoTech 2023-2024
 */
package cn.yishotech.starter.page;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <p>类路径:cn.yishotech.starter.page.PageResp</p>
 * <p>类描述:分页响应</p>
 * <p>创建人:jason zong</p>
 * <p>创建时间:2024/10/12 02:24</p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "分页响应")
public class PageResp<T> {

    @Schema(description = "数据列表")
    private List<T> records;
    @Schema(description = "总记录数")
    private long total;
    @Schema(description = "当前页数")
    private long pageNum;
    @Schema(description = "每页记录数")
    private long pageSize;
    @Schema(description = "总页数")
    private long pages;

}
