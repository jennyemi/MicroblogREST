package com.mycompany.microblog.entities;

import com.mycompany.microblog.entities.Utente;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-12T19:40:03")
@StaticMetamodel(Post.class)
public class Post_ { 

    public static volatile SingularAttribute<Post, Utente> utente;
    public static volatile SingularAttribute<Post, Date> dataOra;
    public static volatile SingularAttribute<Post, String> titolo;
    public static volatile SingularAttribute<Post, Long> id;
    public static volatile SingularAttribute<Post, String> testo;

}