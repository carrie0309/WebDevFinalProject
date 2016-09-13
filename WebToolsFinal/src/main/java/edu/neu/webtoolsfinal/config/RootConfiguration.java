/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.webtoolsfinal.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 * @author Jiayang Shen
 */
@Configuration
@ComponentScan({"edu.neu.webtoolsfinal.*"})
@Import({MvcConfiguration.class, RepositoryConfiguration.class, SecurityConfiguration.class})
public class RootConfiguration {
}
