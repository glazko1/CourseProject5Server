package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_statuses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserStatus {

    @Id
    @Column(name = "status_id")
    private int statusId;

    @Column(name = "status_name")
    private String statusName;
}