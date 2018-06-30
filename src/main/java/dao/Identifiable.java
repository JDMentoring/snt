package dao;

import java.io.Serializable;

public interface Identifiable<ID extends Integer>{
    ID getId();
}
