package fitnessreportssvc.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "membership_reports")
public class MembershipReport {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private LocalDate month;

    @Column(nullable = false)
    private int activeCount;

    @Column(nullable = false)
    private int expiredCount;

    @Column(nullable = false)
    private BigDecimal totalRevenue;
}
