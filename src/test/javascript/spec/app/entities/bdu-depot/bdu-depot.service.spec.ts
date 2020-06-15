import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { BduDepotService } from 'app/entities/bdu-depot/bdu-depot.service';
import { IBduDepot, BduDepot } from 'app/shared/model/bdu-depot.model';

describe('Service Tests', () => {
  describe('BduDepot Service', () => {
    let injector: TestBed;
    let service: BduDepotService;
    let httpMock: HttpTestingController;
    let elemDefault: IBduDepot;
    let expectedResult: IBduDepot | IBduDepot[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(BduDepotService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new BduDepot(0, 'AAAAAAA', 'AAAAAAA', 0, 0, 0, 0, 0, 0, 0, 0, currentDate, 0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateDepot: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a BduDepot', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateDepot: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateDepot: currentDate,
          },
          returnedFromService
        );

        service.create(new BduDepot()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a BduDepot', () => {
        const returnedFromService = Object.assign(
          {
            code: 'BBBBBB',
            numEnreg: 'BBBBBB',
            natureDepot: 1,
            paysResidence: 1,
            ville: 1,
            natureDeposant: 1,
            statutJuridique: 1,
            sexeDeposant: 1,
            secteurActivite: 1,
            tailleEntreprise: 1,
            dateDepot: currentDate.format(DATE_FORMAT),
            termeDepot: 1,
            montantDepot: 1,
            tauxRenumeration: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateDepot: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of BduDepot', () => {
        const returnedFromService = Object.assign(
          {
            code: 'BBBBBB',
            numEnreg: 'BBBBBB',
            natureDepot: 1,
            paysResidence: 1,
            ville: 1,
            natureDeposant: 1,
            statutJuridique: 1,
            sexeDeposant: 1,
            secteurActivite: 1,
            tailleEntreprise: 1,
            dateDepot: currentDate.format(DATE_FORMAT),
            termeDepot: 1,
            montantDepot: 1,
            tauxRenumeration: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateDepot: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a BduDepot', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
