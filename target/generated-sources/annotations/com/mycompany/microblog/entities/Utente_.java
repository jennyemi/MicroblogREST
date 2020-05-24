package com.mycompany.microblog.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-23T18:00:35")
@StaticMetamodel(Utente.class)
public class Utente_ { 

    public static volatile SingularAttribute<Utente, String> password;
    public static volatile SingularAttribute<Utente, String> salt;
    public static volatile SingularAttribute<Utente, String> permissions;
    public static volatile SingularAttribute<Utente, Long> id;
    public static volatile SingularAttribute<Utente, String> username;

}