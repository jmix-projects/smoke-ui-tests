package com.company.samplesales.app;

import com.company.samplesales.entity.User;
import io.jmix.ldap.userdetails.AbstractLdapUserDetailsSynchronizationStrategy;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.stereotype.Component;

@Component("sales_LdapConfigurationBean")
public class LdapConfigurationBean extends AbstractLdapUserDetailsSynchronizationStrategy<User> {

    @Override
    protected Class<User> getUserClass() {
        return User.class;
    }

    @Override
    protected void mapUserDetailsAttributes(User userDetails, DirContextOperations ctx) {
        String firstName = "";

        if (ctx.getStringAttribute("givenName")!=null) {
            firstName = ctx.getStringAttribute("givenName");
        }
        if (ctx.getStringAttribute("cn")!=null) {
            firstName = ctx.getStringAttribute("cn");
        }
        userDetails.setFirstName(firstName);
        userDetails.setLastName(ctx.getStringAttribute("sn"));
    }
}

