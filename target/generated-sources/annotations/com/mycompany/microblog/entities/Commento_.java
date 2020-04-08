package com.mycompany.microblog.entities;

import com.mycompany.microblog.entities.Post;
import com.mycompany.microblog.entities.Utente;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-02T23:02:09")
@StaticMetamodel(Commento.class)
public class Commento_ { 

    public static volatile SingularAttribute<Commento, Utente> utente;
    public static volatile SingularAttribute<Commento, Date> dataOra;
    public static volatile SingularAttribute<Commento, Post> post;
    public static volatile SingularAttribute<Commento, Long> id;
    public static volatile SingularAttribute<Commento, String> testo;

}