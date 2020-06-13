import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBankInfos } from 'app/shared/model/bank-infos.model';

type EntityResponseType = HttpResponse<IBankInfos>;
type EntityArrayResponseType = HttpResponse<IBankInfos[]>;

@Injectable({ providedIn: 'root' })
export class BankInfosService {
  public resourceUrl = SERVER_API_URL + 'api/bank-infos';

  constructor(protected http: HttpClient) {}

  create(bankInfos: IBankInfos): Observable<EntityResponseType> {
    return this.http.post<IBankInfos>(this.resourceUrl, bankInfos, { observe: 'response' });
  }

  update(bankInfos: IBankInfos): Observable<EntityResponseType> {
    return this.http.put<IBankInfos>(this.resourceUrl, bankInfos, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IBankInfos>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBankInfos[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
