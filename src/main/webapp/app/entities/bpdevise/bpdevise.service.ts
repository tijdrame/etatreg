import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBpdevise } from 'app/shared/model/bpdevise.model';

type EntityResponseType = HttpResponse<IBpdevise>;
type EntityArrayResponseType = HttpResponse<IBpdevise[]>;

@Injectable({ providedIn: 'root' })
export class BpdeviseService {
  public resourceUrl = SERVER_API_URL + 'api/bpdevises';

  constructor(protected http: HttpClient) {}

  create(bpdevise: IBpdevise): Observable<EntityResponseType> {
    return this.http.post<IBpdevise>(this.resourceUrl, bpdevise, { observe: 'response' });
  }

  update(bpdevise: IBpdevise): Observable<EntityResponseType> {
    return this.http.put<IBpdevise>(this.resourceUrl, bpdevise, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IBpdevise>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBpdevise[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
