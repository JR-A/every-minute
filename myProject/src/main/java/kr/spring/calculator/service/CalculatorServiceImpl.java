package kr.spring.calculator.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.calculator.dao.CalculatorMapper;

@Service("calculatorService")
public class CalculatorServiceImpl implements CalculatorService {
	@Resource
	private CalculatorMapper calculatorMapper;
}
