import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBpbqe } from 'app/shared/model/bpbqe.model';

type EntityResponseType = HttpResponse<IBpbqe>;
type EntityArrayResponseType = HttpResponse<IBpbqe[]>;

@Injectable({ providedIn: 'root' })
export class BpbqeService {
  public resourceUrl = SERVER_API_URL + 'api/bpbqes';

  constructor(protected http: HttpClient) {}

  create(bpbqe: IBpbqe): Observable<EntityResponseType> {
    return this.http.post<IBpbqe>(this.resourceUrl, bpbqe, { observe: 'response' });
  }

  update(bpbqe: IBpbqe): Observable<EntityResponseType> {
    return this.http.put<IBpbqe>(this.resourceUrl, bpbqe, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IBpbqe>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBpbqe[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
