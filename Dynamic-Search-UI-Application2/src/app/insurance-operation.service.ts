import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SearchRequest } from './search-request';
import { SearchResponse } from './search-response';

@Injectable({
  providedIn: 'root'
})
export class InsuranceOperationService {

  constructor(private httpClient:HttpClient) {

  }

  getPlanNames() : Observable<any> {
   return this.httpClient.get<any>(`http://localhost:8082/BootRestProj03-DynamicSearch-InExleAndPdf/search-api/planname`);
  }

  getPlanStatus() : Observable<any>{
   return this.httpClient.get<any>(`http://localhost:8082/BootRestProj03-DynamicSearch-InExleAndPdf/search-api/planstatus`);
  }

  searchPlans(request : SearchRequest) : Observable<SearchResponse[]>{
   return this.httpClient.post<SearchResponse[]>(`http://localhost:8082/BootRestProj03-DynamicSearch-InExleAndPdf/search-api/search`, request);
  }

  getExcelReport() {
   return this.httpClient.get<any>(`http://localhost:8082/BootRestProj03-DynamicSearch-InExleAndPdf/search-api/excelreport`, {responseType : `arraybuffer` as `json`});
  }

  getPdfReport() {
   return this.httpClient.get<any>(`http://localhost:8082/BootRestProj03-DynamicSearch-InExleAndPdf/search-api/pdfreport`, {responseType : `arraybuffer` as `json`});
  }

}
