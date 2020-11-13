import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBpnaema } from 'app/shared/model/bpnaema.model';

type EntityResponseType = HttpResponse<IBpnaema>;
type EntityArrayResponseType = HttpResponse<IBpnaema[]>;

@Injectable({ providedIn: 'root' })
export class BpnaemaService {
  public resourceUrl = SERVER_API_URL + 'api/bpnaemas';

  constructor(protected http: HttpClient) {}

  create(bpnaema: IBpnaema): Observable<EntityResponseType> {
    return this.http.post<IBpnaema>(this.resourceUrl, bpnaema, { observe: 'response' });
  }

  update(bpnaema: IBpnaema): Observable<EntityResponseType> {
    return this.http.put<IBpnaema>(this.resourceUrl, bpnaema, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IBpnaema>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBpnaema[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
