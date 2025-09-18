package investment.control.dto.InvestmentDTO;

import investment.control.model.TypeInvestment;
import investment.control.model.User;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record InvestmentRequestDTO(
        @NotNull(message = "choice a type if investment(REIT, STOCK, CRYPTO)")
        TypeInvestment type,
        @NotBlank(message = "name is required")
        String name,
        @NotBlank(message = "email is required")
        @Positive
        Double quantity,
        @NotBlank(message = "average price is required")
        @Positive
        Double averagePrice,
        @NotBlank(message = "purchase date is required")
        LocalDate purchaseDate) {

}
