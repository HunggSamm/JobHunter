package vn.hoidanit.jobhunter.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.hoidanit.jobhunter.domain.Company;
import vn.hoidanit.jobhunter.domain.dto.ResultPaginationDTO;
import vn.hoidanit.jobhunter.service.CompanyService;

import java.util.List;
import java.util.Optional;

@RestController
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }
    @PostMapping("/companies")
    public ResponseEntity<Company> createCompany(@Valid @RequestBody Company company)
    {
        return ResponseEntity.status(HttpStatus.OK).body(this.companyService.handleCreateCompany(company));
    }
    @GetMapping("/companies")
    public ResponseEntity<ResultPaginationDTO> getAllCompany(@RequestParam("current") Optional<String> currentOptional,
                                                             @RequestParam("pageSize") Optional<String> pageSizeOptional)
    {
        // Phân trang nhờ 2 tham số
        String sCurrent = currentOptional.isPresent() ? currentOptional.get() : "";
        String sPageSize = pageSizeOptional.isPresent() ? pageSizeOptional.get() : "";
        int current = Integer.parseInt(sCurrent);
        int pageSize = Integer.parseInt(sPageSize);
        Pageable pageable = PageRequest.of(current-1, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(this.companyService.fetchAllCompanies(pageable));
    }
    @PutMapping("/companies")
    public ResponseEntity<Company> updateCompany(@Valid @RequestBody Company company)
    {
        Company updateCompany = this.companyService.handleUpdateCompany(company);
        return ResponseEntity.ok(updateCompany);
    }
    @DeleteMapping("/companies/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable("id") long id)
    {
        this.companyService.handleDeleteCompany(id);
        return ResponseEntity.ok(null);
    }
}
