import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Model1 } from '../interfaces/model-1';
import { map, Observable } from 'rxjs';
import { QteminMax } from '../interfaces/qtemin-max';
@Injectable({
  providedIn: 'root'
})
export class DashboardService {


  constructor(private http: HttpClient) { }
  pieChart(add: string): Observable<Array<Model1>> {
    let queryParams = new HttpParams();
    queryParams = queryParams.append("adresse", add);
    return this.http.get<Array<Model1>>("http://localhost:8080/distri/parAdresse", { params: queryParams })
      .pipe(map((d: Array<Model1>) => d));;

  }
  distributionTotal(prod: string, source: string, date1: string, date2: string) {
    prod = `${encodeURIComponent(prod)}`
    let queryParams = new HttpParams();
    /*console.log(date1);*/
    queryParams = queryParams.append("prod", prod);
    queryParams = queryParams.append("source", source);
    queryParams = queryParams.append("date1", date1);
    queryParams = queryParams.append("date2", date2);
    return this.http.get<Array<Model1>>("http://localhost:8080/distri/distributiontotal", { params: queryParams })
      .pipe(map((d: Array<Model1>) => d)
      );
  }
  adresse() {
    return this.http.get<string[]>("http://localhost:8080/distri/adresse");
  }
  qtemax() {
    return this.http.get<Array<QteminMax>>("http://localhost:8080/distri/qteMax")
      .pipe(map((d: Array<QteminMax>) => d));
  }
  qtemin() {
    return this.http.get<Array<QteminMax>>("http://localhost:8080/distri/qteMin")
      .pipe(map((d: Array<QteminMax>) => d));
  }
  ville() {
    return this.http.get<string[]>("http://localhost:8080/distri/ville",);
  }
  nomdist(ville: string) {
    let queryParams = new HttpParams();

    queryParams = queryParams.append("ville", ville);
    return this.http.get<string[]>("http://localhost:8080/distri/nomdist", { params: queryParams });
  }
  proddes() {
    return this.http.get<string[]>("http://localhost:8080/distri/proddes");
  }
  source() {
    return this.http.get<string[]>("http://localhost:8080/distri/source");
  }
  baretat(proddes: string, ville: string, nomdist: string): Observable<Array<Model1>> {
    proddes = `${encodeURIComponent(proddes)}`
    let queryParams = new HttpParams();
    queryParams = queryParams.append("proddes", proddes);
    queryParams = queryParams.append("ville", ville);
    queryParams = queryParams.append("nomdist", nomdist);
    return this.http.get<Array<Model1>>("http://localhost:8080/distri/etats", { params: queryParams })
      .pipe(map((d: Array<Model1>) => d));;

  }
  enstock(proddes: string): Observable<Array<Model1>> {
    proddes = `${encodeURIComponent(proddes)}`
    let queryParams = new HttpParams();
    queryParams = queryParams.append("prod", proddes);
    return this.http.get<Array<Model1>>("http://localhost:8080/distri/enstock", { params: queryParams })
      .pipe(map((d: Array<Model1>) => d));;

  }
  vendu(proddes: string): Observable<Array<Model1>> {
    proddes = `${encodeURIComponent(proddes)}`
    let queryParams = new HttpParams();
    queryParams = queryParams.append("prod", proddes);

    return this.http.get<Array<Model1>>("http://localhost:8080/distri/vendu", { params: queryParams })
      .pipe(map((d: Array<Model1>) => d));

  }
  ventpardate(prod: string, source: string, date1: string, date2: string) {
    prod = `${encodeURIComponent(prod)}`
    let queryParams = new HttpParams();
    queryParams = queryParams.append("prod", prod);
    queryParams = queryParams.append("source", source);
    queryParams = queryParams.append("date1", date1);
    queryParams = queryParams.append("date2", date2);
    return this.http.get<Array<Model1>>("http://localhost:8080/distri/ventpardate", { params: queryParams })
      .pipe(map((d: Array<Model1>) => d));
  }
}
