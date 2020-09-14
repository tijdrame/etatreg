import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { Bp1ComService } from 'app/entities/bp-1-com/bp-1-com.service';
import { IBp1Com, Bp1Com } from 'app/shared/model/bp-1-com.model';

describe('Service Tests', () => {
  describe('Bp1Com Service', () => {
    let injector: TestBed;
    let service: Bp1ComService;
    let httpMock: HttpTestingController;
    let elemDefault: IBp1Com;
    let expectedResult: IBp1Com | IBp1Com[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(Bp1ComService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Bp1Com(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        currentDate,
        currentDate,
        currentDate,
        'AAAAAAA',
        currentDate
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dou: currentDate.format(DATE_FORMAT),
            ddd: currentDate.format(DATE_FORMAT),
            ddc: currentDate.format(DATE_FORMAT),
            dateArrete: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Bp1Com', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dou: currentDate.format(DATE_FORMAT),
            ddd: currentDate.format(DATE_FORMAT),
            ddc: currentDate.format(DATE_FORMAT),
            dateArrete: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dou: currentDate,
            ddd: currentDate,
            ddc: currentDate,
            dateArrete: currentDate,
          },
          returnedFromService
        );

        service.create(new Bp1Com()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Bp1Com', () => {
        const returnedFromService = Object.assign(
          {
            age: 'BBBBBB',
            dev: 'BBBBBB',
            ncp: 'BBBBBB',
            inti: 'BBBBBB',
            sde: 1,
            cha: 'BBBBBB',
            dou: currentDate.format(DATE_FORMAT),
            ddd: currentDate.format(DATE_FORMAT),
            ddc: currentDate.format(DATE_FORMAT),
            nomFic: 'BBBBBB',
            dateArrete: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dou: currentDate,
            ddd: currentDate,
            ddc: currentDate,
            dateArrete: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Bp1Com', () => {
        const returnedFromService = Object.assign(
          {
            age: 'BBBBBB',
            dev: 'BBBBBB',
            ncp: 'BBBBBB',
            inti: 'BBBBBB',
            sde: 1,
            cha: 'BBBBBB',
            dou: currentDate.format(DATE_FORMAT),
            ddd: currentDate.format(DATE_FORMAT),
            ddc: currentDate.format(DATE_FORMAT),
            nomFic: 'BBBBBB',
            dateArrete: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dou: currentDate,
            ddd: currentDate,
            ddc: currentDate,
            dateArrete: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Bp1Com', () => {
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
