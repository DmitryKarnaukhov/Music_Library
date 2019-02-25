package musiclibrary.entities;

import org.mongodb.morphia.annotations.Embedded;

@Embedded
public enum Genre {
    none,
    Rock,
    Pop,
    Rap,
    Dance,
    Electronic,
    Shanson,
    Reggie
}
