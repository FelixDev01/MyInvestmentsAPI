package investment.control.service;

import investment.control.dto.InvestmentDTO.InvestmentRequestDTO;
import investment.control.dto.InvestmentDTO.InvestmentResponseDTO;
import investment.control.dto.userDTO.UserResponseDTO;
import investment.control.exceptions.NotFoundException;
import investment.control.model.Investment;
import investment.control.model.User;
import investment.control.repository.InvestmentRepository;
import investment.control.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InvestmentService {

    private final InvestmentRepository investmentRepository;
    private final UserRepository userRepository;

    public InvestmentService(InvestmentRepository investmentRepository, UserRepository userRepository) {
        this.investmentRepository = investmentRepository;
        this.userRepository = userRepository;
    }

    /// POST METHOD
    @Transactional
    public InvestmentResponseDTO registerInvestment(InvestmentRequestDTO dto){

        Investment investment = new Investment();
        investment.setType(dto.type());
        investment.setName(dto.name());
        investment.setQuantity(dto.quantity());
        investment.setAveragePrice(dto.averagePrice());
        investment.setPurchaseDate(dto.purchaseDate());

        Investment investmentSaved = investmentRepository.save(investment);
        return new InvestmentResponseDTO(investmentSaved);
    }

    /// GET ALL METHOD
    public Page<InvestmentResponseDTO> findAll(Pageable pageable){
        Page<Investment> investments = investmentRepository.findAll(pageable);
        return investments.map(InvestmentResponseDTO::new);
    }

    /// GET BY ID
    public InvestmentResponseDTO findById(Long id){
        Investment investment = investmentRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Id not Found"));
        return new InvestmentResponseDTO(investment);
    }

    /// UPDATE METHOD - AFTER

    /// DELETE METHOD
    @Transactional
    public void deleteInvestment(Long id){
        investmentRepository.deleteById(id);
    }
}
