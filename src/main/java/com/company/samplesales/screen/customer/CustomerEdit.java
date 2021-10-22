package com.company.samplesales.screen.customer;

import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import com.company.samplesales.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("sales_Customer.edit")
@UiDescriptor("customer-edit.xml")
@EditedEntityContainer("customerDc")
public class CustomerEdit extends StandardEditor<Customer> {

}