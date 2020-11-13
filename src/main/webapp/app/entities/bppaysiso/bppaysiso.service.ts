import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBppaysiso } from 'app/shared/model/bppaysiso.model';

type EntityResponseType = HttpResponse<IBppaysiso>;
type EntityArrayResponseType = HttpResponse<IBppaysiso[]>;

@Injectable({ providedIn: 'root' })
export class BppaysisoService {
  public resourceUrl = SERVER_API_URL + 'api/bppaysisos';

  constructor(protected http: HttpClient) {}

  create(bppaysiso: IBppaysiso): Observable<EntityResponseType> {
    return this.http.post<IBppaysiso>(this.resourceUrl, bppaysiso, { observe: 'response' });
  }

  update(bppaysiso: IBppaysiso): Observable<EntityResponseType> {
    return this.http.put<IBppaysiso>(this.resourceUrl, bppaysiso, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IBppaysiso>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBppaysiso[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
