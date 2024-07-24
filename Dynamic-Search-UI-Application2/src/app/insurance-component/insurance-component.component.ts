import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validator, FormBuilder, ReactiveFormsModule, FormsModule} from '@angular/forms';
import {tap} from 'rxjs/operators';

import { InsuranceOperationService } from '../insurance-operation.service';
import { SearchRequest } from '../search-request';
import { SearchResponse } from '../search-response';

@Component({
  selector: 'app-insurance-component',
  templateUrl: './insurance-component.component.html',
  styleUrl: './insurance-component.component.css'
})
export class InsuranceComponentComponent implements OnInit{
  
  constructor(private insuranceService : InsuranceOperationService){ }

  public planNames : string[] | undefined;
  public planStatuses : any;

  public selectedPlan="select";
  public selectedStatus="select";

  searchRequest : SearchRequest=new SearchRequest();
  searchResponse : SearchResponse [] = [];

  ngOnInit(): void {
    this.getPlanNames();
    this.getPlanStatus();
    
  }


  getPlanNames(){
    this.insuranceService.getPlanNames().subscribe(data => {
      this.planNames=data;
    });
  }

  getPlanStatus(){
    this.insuranceService.getPlanStatus().subscribe(data => {
      this.planStatuses=data;
    });
  }

  search(){
    this.searchRequest.planName=this.selectedPlan;
    this.searchRequest.planStatus=this.selectedStatus;
    this.insuranceService.searchPlans(this.searchRequest).subscribe(data=>{
         this.searchResponse=data;   
    });
  }

  onSubmit(){
    this.search();
  }

  exportToExcel(){
    this.insuranceService.getExcelReport().subscribe(data => {
      let file=new Blob([data], {type : 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'});
      var fileURL = URL.createObjectURL(file);
      window.open(fileURL);
    });
  }

  exportToPdf(){
   this.insuranceService.getPdfReport().subscribe(data => {
    let file= new Blob([data], {type: 'application/pdf'});
    var fileURL=URL.createObjectURL(file);
    window.open(fileURL);
   }); 
  }

}
