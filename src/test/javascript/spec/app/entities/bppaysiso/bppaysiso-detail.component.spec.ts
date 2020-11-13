import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { BppaysisoDetailComponent } from 'app/entities/bppaysiso/bppaysiso-detail.component';
import { Bppaysiso } from 'app/shared/model/bppaysiso.model';

describe('Component Tests', () => {
  describe('Bppaysiso Management Detail Component', () => {
    let comp: BppaysisoDetailComponent;
    let fixture: ComponentFixture<BppaysisoDetailComponent>;
    const route = ({ data: of({ bppaysiso: new Bppaysiso(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [BppaysisoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(BppaysisoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BppaysisoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load bppaysiso on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.bppaysiso).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
