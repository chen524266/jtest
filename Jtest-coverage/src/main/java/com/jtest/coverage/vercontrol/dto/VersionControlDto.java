package com.jtest.coverage.vercontrol.dto;

import com.jtest.coverage.vercontrol.enums.CodeManageTypeEnum;
import lombok.Data;

import java.util.List;

/**
 * @ProjectName: code-diff-parent
 * @Package: com.dr.code.diff.dto
 * @Description: java类作用描述
 * @Author: duanrui
 * @CreateDate: 2021/4/5 10:10
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2021
 */
@Data
public class VersionControlDto {

    /**
     *  远程仓库地址
     */
    private String repoUrl;

    /**
     * git原始分支或tag/svn 版本
     */
    private String baseVersion;

    /**
     * git现分支或tag、svn 版本
     */
    private String nowVersion;


    /**
     * 专用于svn新分支
     */
    private String svnRepoUrl;


    /**
     * 本地旧文件基础地址
     */
    private String oldLocalBasePath;

    /**
     * 本地新文件基础地址
     */
    private String newLocalBasePath;

    /**
     * 版本控制类型
     */
    private CodeManageTypeEnum codeManageTypeEnum;



    private List<DiffEntryDto> diffClasses;

    private  String rootPath;

    private  String userName;

    private  String passWord;

    private  String gitSshPrivateKey;

}
