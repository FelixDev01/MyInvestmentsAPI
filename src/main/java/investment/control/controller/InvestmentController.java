package investment.control.controller;

import investment.control.dto.InvestmentDTO.InvestmentRequestDTO;
import investment.control.dto.InvestmentDTO.InvestmentResponseDTO;
import investment.control.service.InvestmentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/investments")
public class InvestmentController {

    private final InvestmentService service;

    public InvestmentController(InvestmentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<InvestmentResponseDTO> register(@RequestBody @Valid InvestmentRequestDTO dto){
        InvestmentResponseDTO newInvestment = service.registerInvestment(dto);
        return ResponseEntity.ok(newInvestment);
    }

    @GetMapping
    public ResponseEntity<Page<InvestmentResponseDTO>> getAll(Pageable pageable){
        Page<InvestmentResponseDTO> page = service.findAll(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvestmentResponseDTO> findById(@PathVariable Long id){
        InvestmentResponseDTO investmentFound = service.findById(id);
        return ResponseEntity.ok(investmentFound);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inativarUsuario(@PathVariable Long id) {
        service.deleteInvestment(id);
        return ResponseEntity.noContent().build();
    }
}
