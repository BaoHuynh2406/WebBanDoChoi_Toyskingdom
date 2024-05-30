package com.mts.toyskingdom.service.impl;

import com.mts.toyskingdom.data.dto.TestDTO;
import com.mts.toyskingdom.data.model.TestM;
import com.mts.toyskingdom.service.TestSv;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestSvImpl implements TestSv {

    @Override
    public TestM testService(TestDTO testDTO) throws Exception {
        System.out.println(testDTO.getId() + ": " + testDTO.getName());
        TestM test = new TestM(testDTO.getId(), testDTO.getName());
        return test;
    }

    @Override
    public void test1(TestDTO testDTO) throws Exception {
        System.out.println(testDTO.getId() + ": " + testDTO.getName());
    }
}
