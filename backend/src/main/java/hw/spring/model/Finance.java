package hw.spring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Finance {
    @GeneratedValue
    @Id
    private int id;

    /**
     * hack so that my idea with unique row in a table works for crudrepository and there is no need to write a custom
     * SQL query for find/save
     */
    @Column
    private String name;

    @Column
    @ColumnDefault("0")
    private BigDecimal revenue;

    public Finance copy() {
        return new Finance(id, name, revenue);
    }
}
