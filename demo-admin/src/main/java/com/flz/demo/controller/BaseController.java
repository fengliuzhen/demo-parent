package com.flz.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class BaseController {

    @Autowired
    public HttpServletRequest request;
}
