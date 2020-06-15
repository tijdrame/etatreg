import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { BduEffetService } from 'app/entities/bdu-effet/bdu-effet.service';
import { IBduEffet, BduEffet } from 'app/shared/model/bdu-effet.model';

describe('Service Tests', () => {
  describe('BduEffet Service', () => {
    let injector: TestBed;
    let service: BduEffetService;
    let httpMock: HttpTestingController;
    let elemDefault: IBduEffet;
    let expectedResult: IBduEffet | IBduEffet[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(BduEffetService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new BduEffet(0, 'AAAAAAA', 'AAAAAAA', 0, 0, 0, 0, currentDate, currentDate, 0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateEscompte: currentDate.format(DATE_FORMAT),
            dateEcheance: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a BduEffet', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateEscompte: currentDate.format(DATE_FORMAT),
            dateEcheance: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateEscompte: currentDate,
            dateEcheance: currentDate,
          },
          returnedFromService
        );

        service.create(new BduEffet()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a BduEffet', () => {
        const returnedFromService = Object.assign(
          {
            code: 'BBBBBB',
            numEnreg: 'BBBBBB',
            natureDeposant: 1,
            paysResidence: 1,
            ville: 1,
            montantEffet: 1,
            dateEscompte: currentDate.format(DATE_FORMAT),
            dateEcheance: currentDate.format(DATE_FORMAT),
            nbreJours: 1,
            tauxInteret: 1,
            montantCharges: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateEscompte: currentDate,
            dateEcheance: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of BduEffet', () => {
        const returnedFromService = Object.assign(
          {
            code: 'BBBBBB',
            numEnreg: 'BBBBBB',
            natureDeposant: 1,
            paysResidence: 1,
            ville: 1,
            montantEffet: 1,
            dateEscompte: currentDate.format(DATE_FORMAT),
            dateEcheance: currentDate.format(DATE_FORMAT),
            nbreJours: 1,
            tauxInteret: 1,
            montantCharges: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateEscompte: currentDate,
            dateEcheance: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a BduEffet', () => {
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
