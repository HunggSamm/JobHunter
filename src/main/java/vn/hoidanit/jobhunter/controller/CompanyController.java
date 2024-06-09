package vn.hoidanit.jobhunter.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.hoidanit.jobhunter.domain.Company;
import vn.hoidanit.jobhunter.service.CompanyService;

import java.util.List;

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
    public ResponseEntity<List<Company>> getAllCompany()
    {
        List<Company> companies = this.companyService.fetchAllCompanies();
        return ResponseEntity.ok(companies);
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
