package ht.henrique.mazebank.model.database;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.math.BigDecimal;
import java.text.DecimalFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
@Table(name = "users")
public class User implements Projection{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id")
    private ObjectId uid;
    @Column(name = "_userName")
    private String user_name;
    @Column(name = "_userPass")
    private String user_pass;
    @Column(name = "_balance")
    private BigDecimal user_balance;
    @Column(name = "_userEmail")
    private String user_email;
}
