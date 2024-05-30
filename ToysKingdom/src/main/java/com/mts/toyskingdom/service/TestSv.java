package com.mts.toyskingdom.service;

import com.mts.toyskingdom.data.dto.TestDTO;
import com.mts.toyskingdom.data.model.TestM;

public interface TestSv {

     TestM testService(TestDTO testDTO) throws Exception;

     void test1(TestDTO testDTO) throws Exception;
}
