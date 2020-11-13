import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBpitrs } from 'app/shared/model/bpitrs.model';

type EntityResponseType = HttpResponse<IBpitrs>;
type EntityArrayResponseType = HttpResponse<IBpitrs[]>;

@Injectable({ providedIn: 'root' })
export class BpitrsService {
  public resourceUrl = SERVER_API_URL + 'api/bpitrs';

  constructor(protected http: HttpClient) {}

  create(bpitrs: IBpitrs): Observable<EntityResponseType> {
    return this.http.post<IBpitrs>(this.resourceUrl, bpitrs, { observe: 'response' });
  }

  update(bpitrs: IBpitrs): Observable<EntityResponseType> {
    return this.http.put<IBpitrs>(this.resourceUrl, bpitrs, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IBpitrs>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBpitrs[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
