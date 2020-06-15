import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDechargement } from 'app/shared/model/dechargement.model';

type EntityResponseType = HttpResponse<IDechargement>;
type EntityArrayResponseType = HttpResponse<IDechargement[]>;

@Injectable({ providedIn: 'root' })
export class DechargementService {
  public resourceUrl = SERVER_API_URL + 'api/dechargements';

  constructor(protected http: HttpClient) {}

  create(dechargement: IDechargement): Observable<EntityResponseType> {
    return this.http.post<IDechargement>(this.resourceUrl, dechargement, { observe: 'response' });
  }

  update(dechargement: IDechargement): Observable<EntityResponseType> {
    return this.http.put<IDechargement>(this.resourceUrl, dechargement, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDechargement>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDechargement[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
