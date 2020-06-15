import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { BduCdbDetailComponent } from 'app/entities/bdu-cdb/bdu-cdb-detail.component';
import { BduCdb } from 'app/shared/model/bdu-cdb.model';

describe('Component Tests', () => {
  describe('BduCdb Management Detail Component', () => {
    let comp: BduCdbDetailComponent;
    let fixture: ComponentFixture<BduCdbDetailComponent>;
    const route = ({ data: of({ bduCdb: new BduCdb(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [BduCdbDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(BduCdbDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BduCdbDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load bduCdb on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.bduCdb).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
