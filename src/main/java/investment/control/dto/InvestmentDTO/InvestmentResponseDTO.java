package investment.control.dto.InvestmentDTO;

import investment.control.model.Investment;
import investment.control.model.TypeInvestment;
import investment.control.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record InvestmentResponseDTO(
        TypeInvestment type,
        String name,
        Double quantity,
        Double averagePrice,
        LocalDate purchaseDate) {
    public InvestmentResponseDTO(Investment investmentSaved) {
        this(investmentSaved.getType()
            , investmentSaved.getName()
            , investmentSaved.getQuantity()
            , investmentSaved.getAveragePrice()
            , investmentSaved.getPurchaseDate());
    }
}
