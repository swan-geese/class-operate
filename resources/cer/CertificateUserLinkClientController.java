/*
 * Copyright (C), 2008-2021, xxx All Rights Reserved.
 */
package com.xxx.admin.web.cer;

import com.xxx.common.dto.ConditionDTO;
import com.xxx.common.dto.ConditionStatusDTO;
import com.xxx.common.dto.IdsDTO;
import com.xxx.error.common.Response;
import com.xxx.idm.client.cer.client.CertificateUserLinkClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author huangwh
 * @since  2020-10-23
 */
@SuppressWarnings({ "rawtypes" })
@RequestMapping("/cer/certificate-user-link")
@RestController
@Slf4j
public class CertificateUserLinkClientController {

    @Resource
    private CertificateUserLinkClient certificateUserLinkClient;
    @PostMapping("/apply")
    Response apply(@RequestBody Map<String, String> map) {
        return certificateUserLinkClient.apply(map);
    }

    @PostMapping("/list")
    Response list(@RequestBody ConditionStatusDTO conditionDTO) {
        return certificateUserLinkClient.list(conditionDTO);
    }

    @PostMapping("/revoke")
    Response revoke(@RequestBody IdsDTO idsDTO) {
        return certificateUserLinkClient.revoke(idsDTO);
    }

    @GetMapping("/pwd/{cerSerialNumber:.*}")
    Response pwd(@PathVariable("cerSerialNumber") String cerSerialNumber) {
        return certificateUserLinkClient.pwd(cerSerialNumber);
    }

    @GetMapping("/userCerPwd/{userId}")
    Response userCerPwd(@PathVariable("userId") Long userId) {
        return certificateUserLinkClient.userCerPwd(userId);
    }

    @GetMapping("/userCerPwd/uid/{uid:.*}")
    Response userCerPwdByUid() {
        return certificateUserLinkClient.userCerPwdByUid();
    }

    @PostMapping("/history/{id}")
    Response history(@PathVariable("id") Long id, @RequestBody ConditionDTO conditionDTO) {
        return certificateUserLinkClient.history(id, conditionDTO);
    }

    @PostMapping("/rootCer/download")
    public void download(HttpServletResponse response) {
        certificateUserLinkClient.download(response);
    }

    @GetMapping("/uidCer/download/{uid:.*}")
    public void uidCerDownload(HttpServletResponse response) {
        certificateUserLinkClient.uidCerDownload(response);
    }

    @GetMapping("/userCer/download/{userId}")
    void userCerDownload(@PathVariable("userId") Long userId, HttpServletResponse response) {
        certificateUserLinkClient.userCerDownload(userId, response);
    }

}
