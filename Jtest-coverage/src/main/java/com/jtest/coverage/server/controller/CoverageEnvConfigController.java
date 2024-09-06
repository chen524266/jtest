package com.jtest.coverage.server.controller;import java.util.List;import com.Jtest.common.annotation.Log;import com.Jtest.common.core.controller.BaseController;import com.Jtest.common.core.domain.AjaxResult;import com.Jtest.common.core.page.TableDataInfo;import com.Jtest.common.enums.BusinessType;import com.Jtest.common.utils.poi.ExcelUtil;import com.jtest.coverage.server.domain.CoverageEnvConfig;import org.apache.shiro.authz.annotation.RequiresPermissions;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Controller;import org.springframework.ui.ModelMap;import org.springframework.web.bind.annotation.GetMapping;import org.springframework.web.bind.annotation.PathVariable;import org.springframework.web.bind.annotation.PostMapping;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.ResponseBody;import com.jtest.coverage.server.service.ICoverageEnvConfigService;/** * 环境配置Controller * * @author Jtest * @date 2024-09-02 */@Controller@RequestMapping("/server/config")public class CoverageEnvConfigController extends BaseController {    private String prefix = "server/config";    @Autowired    private ICoverageEnvConfigService coverageEnvConfigService;    @RequiresPermissions("server:config:view")    @GetMapping()    public String config() {        return prefix + "/config";    }    /**     * 查询环境配置列表     */    @RequiresPermissions("server:config:list")    @PostMapping("/list")    @ResponseBody    public TableDataInfo list(CoverageEnvConfig coverageEnvConfig) {        startPage();        List<CoverageEnvConfig> list = coverageEnvConfigService.selectCoverageEnvConfigList(coverageEnvConfig);        return getDataTable(list);    }    /**     * 导出环境配置列表     */    @RequiresPermissions("server:config:export")    @Log(title = "环境配置" , businessType = BusinessType.EXPORT)    @PostMapping("/export")    @ResponseBody    public AjaxResult export(CoverageEnvConfig coverageEnvConfig) {        List<CoverageEnvConfig> list = coverageEnvConfigService.selectCoverageEnvConfigList(coverageEnvConfig);        ExcelUtil<CoverageEnvConfig> util = new ExcelUtil<CoverageEnvConfig>(CoverageEnvConfig.class);        return util.exportExcel(list, "环境配置数据");    }    /**     * 新增环境配置     */    @GetMapping("/add")    public String add() {        return prefix + "/add";    }    /**     * 新增保存环境配置     */    @RequiresPermissions("server:config:add")    @Log(title = "环境配置" , businessType = BusinessType.INSERT)    @PostMapping("/add")    @ResponseBody    public AjaxResult addSave(CoverageEnvConfig coverageEnvConfig) {        return toAjax(coverageEnvConfigService.insertCoverageEnvConfig(coverageEnvConfig));    }    /**     * 修改环境配置     */    @RequiresPermissions("server:config:edit")    @GetMapping("/edit/{configId}")    public String edit(@PathVariable("configId") int configId, ModelMap mmap) {        CoverageEnvConfig coverageEnvConfig = coverageEnvConfigService.selectCoverageEnvConfigByConfigId(configId);        mmap.put("coverageEnvConfig" , coverageEnvConfig);        return prefix + "/edit";    }    /**     * 修改保存环境配置     */    @RequiresPermissions("server:config:edit")    @Log(title = "环境配置" , businessType = BusinessType.UPDATE)    @PostMapping("/edit")    @ResponseBody    public AjaxResult editSave(CoverageEnvConfig coverageEnvConfig) {        return toAjax(coverageEnvConfigService.updateCoverageEnvConfig(coverageEnvConfig));    }    /**     * 删除环境配置     */    @RequiresPermissions("server:config:remove")    @Log(title = "环境配置" , businessType = BusinessType.DELETE)    @PostMapping("/remove")    @ResponseBody    public AjaxResult remove(String ids) {        return toAjax(coverageEnvConfigService.deleteCoverageEnvConfigByConfigIds(ids));    }    /**     * 用户手动触发生成报告     */    @Log(title = "环境配置" , businessType = BusinessType.DELETE)    @PostMapping("/report/{configId}")    @ResponseBody    public AjaxResult report( CoverageEnvConfig coverageEnvConfig) {        coverageEnvConfigService.report(coverageEnvConfig.getConfigId());        return toAjax(true);    }}