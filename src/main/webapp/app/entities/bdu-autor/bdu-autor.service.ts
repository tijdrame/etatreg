import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBduAutor } from 'app/shared/model/bdu-autor.model';

type EntityResponseType = HttpResponse<IBduAutor>;
type EntityArrayResponseType = HttpResponse<IBduAutor[]>;

@Injectable({ providedIn: 'root' })
export class BduAutorService {
  public resourceUrl = SERVER_API_URL + 'api/bdu-autors';

  constructor(protected http: HttpClient) {}

  create(bduAutor: IBduAutor): Observable<EntityResponseType> {
    return this.http.post<IBduAutor>(this.resourceUrl, bduAutor, { observe: 'response' });
  }

  update(bduAutor: IBduAutor): Observable<EntityResponseType> {
    return this.http.put<IBduAutor>(this.resourceUrl, bduAutor, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IBduAutor>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBduAutor[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
