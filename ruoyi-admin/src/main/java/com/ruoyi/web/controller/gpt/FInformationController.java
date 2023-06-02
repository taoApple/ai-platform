package com.ruoyi.web.controller.gpt;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.gpt.domain.FInformation;
import com.ruoyi.gpt.service.IFInformationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 资料信息Controller
 * 
 * @author ruoyi
 * @date 2023-06-02
 */
@Api("资料信息管理")
@RestController
@RequestMapping("/gpt/information")
public class FInformationController extends BaseController
{
    @Autowired
    private IFInformationService fInformationService;

    /**
     * 查询资料信息列表
     */
    @ApiOperation("查询资料信息列表")
    @PreAuthorize("@ss.hasPermi('gpt:information:list')")
    @GetMapping("/list")
    public TableDataInfo list(FInformation fInformation)
    {
        startPage();
        List<FInformation> list = fInformationService.selectFInformationList(fInformation);
        return getDataTable(list);
    }

    /**
     * 导出资料信息列表
     */
    @ApiOperation("导出资料信息列表")
    @PreAuthorize("@ss.hasPermi('gpt:information:export')")
    @Log(title = "资料信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FInformation fInformation)
    {
        List<FInformation> list = fInformationService.selectFInformationList(fInformation);
        ExcelUtil<FInformation> util = new ExcelUtil<FInformation>(FInformation.class);
        util.exportExcel(response, list, "资料信息数据");
    }

    /**
     * 获取资料信息详细信息
     */
    @ApiOperation("获取资料信息详细信息")
    @PreAuthorize("@ss.hasPermi('gpt:information:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(fInformationService.selectFInformationById(id));
    }

    /**
     * 新增资料信息
     */
    @ApiOperation("新增资料信息")
    @PreAuthorize("@ss.hasPermi('gpt:information:add')")
    @Log(title = "资料信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FInformation fInformation)
    {
        fInformation.setCreaetUserId(getUserId());
        return toAjax(fInformationService.insertFInformation(fInformation));
    }

    /**
     * 修改资料信息
     */
    @ApiOperation("修改资料信息")
    @PreAuthorize("@ss.hasPermi('gpt:information:edit')")
    @Log(title = "资料信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FInformation fInformation)
    {
        return toAjax(fInformationService.updateFInformation(fInformation));
    }

    /**
     * 删除资料信息
     */
    @ApiOperation("删除资料信息")
    @PreAuthorize("@ss.hasPermi('gpt:information:remove')")
    @Log(title = "资料信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(fInformationService.deleteFInformationByIds(ids));
    }
}
