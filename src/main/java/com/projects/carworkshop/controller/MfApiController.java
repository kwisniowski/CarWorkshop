package com.projects.carworkshop.controller;

        import com.projects.carworkshop.mfApi.MfApiClient;
        import com.projects.carworkshop.mfApi.MfApiResponseDto;
        import com.projects.carworkshop.mfApi.SubjectDto;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/carworkshop/mfapi/")
public class MfApiController {

    @Autowired
    MfApiClient client;

    @RequestMapping(method = RequestMethod.GET, value = "getCustomerInfoByNip/{requestNip}")
    public SubjectDto getCustomerInfoByNip(@PathVariable String requestNip) {
        MfApiResponseDto response = client.getCustomerInfoFromMFByNip(requestNip);
        System.out.println(response.getResult().getSubject().getName());
        return response.getResult().getSubject();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getCustomerInfoByRegon/{requestRegon}")
    public void getCustomerInfoByRegon(@PathVariable String requestRegon) {
        MfApiResponseDto response = client.getCustomerInfoFromMFByRegon(requestRegon);
        System.out.println(response.getResult().getSubject().getName());
    }
}
