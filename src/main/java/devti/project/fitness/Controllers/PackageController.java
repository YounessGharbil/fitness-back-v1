package devti.project.fitness.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import devti.project.fitness.Services.PackageService;
import devti.project.fitness.entities.Package;
import devti.project.fitness.entities.requests.pack.CreatePackageRequest;
import devti.project.fitness.entities.requests.pack.GetPackageResponse;
import devti.project.fitness.entities.requests.pack.UpdatePackageRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/Package")
@RequiredArgsConstructor
public class PackageController {
	
    private final PackageService packageService;
    
    @PostMapping
    public ResponseEntity<Package> createPackage(@RequestBody CreatePackageRequest createPackageRequest) {

        Package pack=Package.builder()
        		.description(createPackageRequest.getDescription())
        		.durationInMonths(createPackageRequest.getDurationInMonths())
        		.packageName(createPackageRequest.getPackageName())
        		.price(createPackageRequest.getPrice())
        		.paymentType(createPackageRequest.getPaymentType())        		
        		.build();
        
        return new ResponseEntity<>(packageService.createPackage(pack), HttpStatus.CREATED);
        
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<GetPackageResponse> getPackage(@PathVariable Long id) {
    	Package pack=packageService.getPackage(id);
        if(pack==null){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        
        GetPackageResponse response=GetPackageResponse.builder()
        		.id(pack.getId())
        		.packageName(pack.getPackageName())
        		.price(pack.getPrice())
        		.description(pack.getDescription())
        		.durationInMonths(pack.getDurationInMonths())
        		.paymentType(pack.getDescription())
        		.build();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    
    @GetMapping
    public ResponseEntity<List<GetPackageResponse>> getPackages() {
    	
    	List<Package> packages=packageService.getPackages();
    	
    	List<GetPackageResponse> response =new ArrayList<GetPackageResponse>();
    	
    	for(Package pack:packages) {
    		response.add(
    				GetPackageResponse.builder()
    				.id(pack.getId())
            		.packageName(pack.getPackageName())
            		.price(pack.getPrice())
            		.description(pack.getDescription())
            		.durationInMonths(pack.getDurationInMonths())
            		.paymentType(pack.getPaymentType().toString())
    				.build()
    				);
    		
    	}

        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Package> updatePackage(@PathVariable Long id,@RequestBody UpdatePackageRequest updatPackageRequest) {
    	Package pack=packageService.getPackage(id);
       
        if(pack==null){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        pack.setPackageName(updatPackageRequest.getPackageName());
        pack.setDescription(updatPackageRequest.getDescription());
        pack.setPrice(updatPackageRequest.getPrice());
        pack.setDurationInMonths(updatPackageRequest.getDurationInMonths());
        pack.setPaymentType(updatPackageRequest.getPaymentType());
        
       

        return new ResponseEntity<>(packageService.updatePackage(pack),HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePackage( @PathVariable Long id) {
        Package pack=packageService.getPackage(id);

        if(pack==null){
            return new ResponseEntity<>("package does not exist",HttpStatus.NOT_FOUND);
        }
        packageService.deletePackage(id);
        return new ResponseEntity<>("deleted successfully",HttpStatus.NO_CONTENT);
    }

    
    
    


}
