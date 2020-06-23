import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPeriode } from 'app/shared/model/periode.model';

type EntityResponseType = HttpResponse<IPeriode>;
type EntityArrayResponseType = HttpResponse<IPeriode[]>;

@Injectable({ providedIn: 'root' })
export class PeriodeService {
  public resourceUrl = SERVER_API_URL + 'api/periodes';

  constructor(protected http: HttpClient) {}

  create(periode: IPeriode): Observable<EntityResponseType> {
    return this.http.post<IPeriode>(this.resourceUrl, periode, { observe: 'response' });
  }

  update(periode: IPeriode): Observable<EntityResponseType> {
    return this.http.put<IPeriode>(this.resourceUrl, periode, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPeriode>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPeriode[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
