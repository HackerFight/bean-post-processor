package com.qiuguan.boot.test.test;

import com.qiuguan.boot.test.processor.Audiar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author created by qiuguan on 2022/1/25 17:51
 */
@Service
public class TestService {

    @Autowired
    private Audiar audiar;

    public void test(){

        audiar.drive();
    }
}
